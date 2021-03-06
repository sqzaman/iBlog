import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingPageComponent } from './modules/home/components/landing-page/landing-page.component';
//import { ProductsComponent } from './modules/products/components/products/products.component';
//import { OrdersComponent } from './modules/orders/components/orders/orders.component';
import { SignupComponent } from './modules/authentication/components/signup/signup.component';
import { SigninComponent } from './modules/authentication/components/signin/signin.component';
import { ProfileComponent } from './modules/authentication/components/profile/profile.component';
import { ProfileEditComponent } from './modules/authentication/components/profile/profile-edit/profile-edit.component';
import { ProfileOverviewComponent } from './modules/authentication/components/profile/profile-overview/profile-overview.component';
//import { ProductComponent } from './modules/products/components/product/product.component';
//import { CartComponent } from './modules/cart/components/cart/cart.component';

// admin route import
import { AdminComponent } from './modules/admin/admin/admin.component';
import { AdminOverviewComponent } from './modules/admin/admin-overview/admin-overview.component';
//import { AdminProductCategoryComponent } from './modules/admin/admin-product-category/admin-product-category.component';
//import { AdminProductComponent } from './modules/admin/admin-product/admin-product.component';
//import { NewCategoryComponent } from './modules/admin/admin-product-category/new-category/new-category.component';
//import { NewProductComponent } from './modules/admin/admin-product/new-product/new-product.component';
//import { UploadProductImageComponent } from './modules/admin/admin-product/upload-product-image/upload-product-image.component';

import { MyArticlesComponent } from './modules/authentication/components/profile/my-articles/my-articles.component';
import { AddNewArtilceComponent } from './modules/authentication/components/profile/add-new-artilce/add-new-artilce.component';

import { AdminAllArticleComponent } from './modules/admin/admin-all-article/admin-all-article.component';
import { AdminApprovalNeedArticleComponent } from './modules/admin/admin-approval-need-article/admin-approval-need-article.component';
import { SearchResultComponent } from './modules/search/components/search-result/search-result.component';
import { ArticleViewComponent } from './modules/article/article-view/article-view.component';

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
        path: 'home',
        component: LandingPageComponent
    },
    {
        path: 'article/:id',
        component: ArticleViewComponent
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
            { path: 'article/new', component: AddNewArtilceComponent },
            { path: 'article/edit/:id', component: AddNewArtilceComponent },
            { path: '', redirectTo:'overview', pathMatch:"full" }
        ]
    }
    ,
    {
        path: 'admin',
        component: AdminComponent,
        children: [
            { path: 'overview', component: AdminOverviewComponent },
            { path: 'article/all', component: AdminAllArticleComponent },
            { path: 'articles/approval-need', component: AdminApprovalNeedArticleComponent },
            { path: '', redirectTo:'overview', pathMatch:"full" }
        ]
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }