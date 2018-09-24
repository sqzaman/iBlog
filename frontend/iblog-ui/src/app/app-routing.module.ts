import { NgModule, ViewChild } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingPageComponent } from './modules/home/components/landing-page/landing-page.component';
import { ProductsComponent } from './modules/products/components/products/products.component';
import { OrdersComponent } from './modules/orders/components/orders/orders.component';
import { SignupComponent } from './modules/authentication/components/signup/signup.component';
import { SigninComponent } from './modules/authentication/components/signin/signin.component';
import { ProfileComponent } from './modules/authentication/components/profile/profile.component';
import { ProfileEditComponent } from './modules/authentication/components/profile/profile-edit/profile-edit.component';
import { ProfileOverviewComponent } from './modules/authentication/components/profile/profile-overview/profile-overview.component';
import { ProductComponent } from './modules/products/components/product/product.component';
import { CartComponent } from './modules/cart/components/cart/cart.component';

// admin route import
import { AdminComponent } from './modules/admin/admin/admin.component';
import { AdminOverviewComponent } from './modules/admin/admin-overview/admin-overview.component';
import { AdminProductCategoryComponent } from './modules/admin/admin-product-category/admin-product-category.component';
import { AdminProductComponent } from './modules/admin/admin-product/admin-product.component';
import { NewCategoryComponent } from './modules/admin/admin-product-category/new-category/new-category.component';
import { NewProductComponent } from './modules/admin/admin-product/new-product/new-product.component';
import { UploadProductImageComponent } from './modules/admin/admin-product/upload-product-image/upload-product-image.component';

import { MyArticlesComponent } from './modules/authentication/components/profile/my-articles/my-articles.component';
import { AddNewArtilceComponent } from './modules/authentication/components/profile/add-new-artilce/add-new-artilce.component';

import {ViewArticleComponent} from './modules/article/view-article/view-article.component';
import { AdminAllArticleComponent } from './modules/admin/admin-all-article/admin-all-article.component';
import { AdminApprovalNeedArticleComponent } from './modules/admin/admin-approval-need-article/admin-approval-need-article.component';
import { SearchResultComponent } from './modules/search/components/search-result/search-result.component';

const routes: Routes = [
    {
        path: 'posts',
        component: SearchResultComponent
    },
    {
        path: '',
        component: LandingPageComponent
    },
    {
        path: 'articles/:id',
        component: ViewArticleComponent
    },
    {
        path: 'products',
        component: ProductsComponent
    },
    {
        path: 'cart',
        component: CartComponent
    },
    {
        path: 'products/:id',
        component: ProductComponent
    },
    {
        path: 'products/category/get/:id',
        component: ProductsComponent
    },
    {
        path: 'orders',
        component: OrdersComponent
    },
    {
        path: 'signup',
        component: SignupComponent
    },
    {
        path: 'signin',
        component: SigninComponent
    }
    ,
    {
        path: 'profile',
        component: ProfileComponent,
        children: [
            { path: 'overview', component: ProfileOverviewComponent },
            { path: 'edit', component: ProfileEditComponent },
            { path: 'my-articles', component: MyArticlesComponent },
            { path: 'add-new-article', component: AddNewArtilceComponent },
            { path: '', redirectTo: 'overview', pathMatch: 'full' }
        ]
    }
    ,
    {
        path: 'admin',
        component: AdminComponent,
        children: [
            { path: 'overview', component: AdminOverviewComponent },
            { path: 'product', component: AdminProductComponent },
            { path: 'product/new', component: NewProductComponent },
            { path: 'product/edit/:id', component: NewProductComponent },
            { path: 'product/image-upload/:id', component: UploadProductImageComponent },
            { path: 'product-category', component: AdminProductCategoryComponent },
            { path: 'product-category/new', component: NewCategoryComponent },
            { path: 'product-category/edit/:id', component: NewCategoryComponent },
            { path: '', redirectTo: 'overview', pathMatch: 'full' }
        ]
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
