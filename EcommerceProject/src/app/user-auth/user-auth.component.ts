import { Component } from '@angular/core';
import { User, UserLogin } from '../classes/user';
import { UserService } from '../services/user.service';
import { STRING_TYPE } from '@angular/compiler';
import { Product } from '../classes/product';
import { Cart } from '../classes/cart';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-user-auth',
  templateUrl: './user-auth.component.html',
  styleUrls: ['./user-auth.component.css']
})
export class UserAuthComponent {

  user: User = new User();
  loginuser: UserLogin = new UserLogin();
  errorText!: string;


  showLogin = true;

  constructor(private userService: UserService,
    private productService: ProductService) { }


  ngOnInit(): void {
    this.userService.userAuthload();


  }

  userSignUp(): void {
    console.log(this.user);
    this.userService.userSignUp(this.user);
    this.errorText = "";
  }

  login(loginUser: UserLogin) {
    this.userService.userLoginMethod(loginUser);
    this.userService.invalidUserAuth.subscribe(
      (result) => {
        if (result) {
          this.errorText = this.userService.errormessage;
          console.log(this.userService.errormessage);
        } else {
          this.localCartToRemoteCart();
        }
      })
  }

  openLogin() {
    this.showLogin = true;
  }

  openSignUp() {
    this.showLogin = false;
  }

  localCartToRemoteCart() {
    let data = localStorage.getItem('localCart')
    let user = localStorage.getItem('user');
    // let costumerId = user && JSON.parse(user).costumerId;
    let costumerId = user && JSON.parse(user).costumerId;

    if (data) {
      let cartDataList: Product[] = JSON.parse(data);
      console.log(cartDataList);

      cartDataList.forEach((product: Product, index) => {
        let cartData: Cart = {
          ...product,
          ...costumerId,
          productId: product.productId,

        };
        //delete cartData.cartId;



        this.productService.addToCart(cartData).subscribe((result) => {
          if (result) {
            console.warn("Item stored In DB");
          }

        })
        if (cartDataList.length === index + 1) {
          localStorage.removeItem('localCart');
        }

      }
      );
    }

    this.productService.getCartList(costumerId);
  }

}


