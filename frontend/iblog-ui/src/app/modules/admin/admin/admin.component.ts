import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../authentication/services/article.service';
import {Article} from '../../authentication/components/profile/model/article';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  articles : Article[];
  totalArticleCount: number;
  totalUnapprovedArticleCount: number = 0;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.articleService.getAllArticles().subscribe(
      (data) => {
         this.articles = JSON.parse(JSON.stringify(data))
         this.totalArticleCount = JSON.parse(JSON.stringify(data)).length;

         this.articles.forEach((e) => {
           if(e.status == 'CREATED') {
            this.totalUnapprovedArticleCount++;
            //console.log(this.totalUnapprovedArticleCount);
           }
         })

           
         });

      console.log(this.articles);
      }

  
}
