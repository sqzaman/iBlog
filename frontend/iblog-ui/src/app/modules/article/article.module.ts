import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ArticleViewComponent } from './article-view/article-view.component';

@NgModule({
  imports: [
    FormsModule,
    CommonModule
  ],
  declarations: [ArticleViewComponent]
})
export class ArticleModule { }
