import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../../services/article.service';

@Component({
  selector: 'app-my-articles',
  templateUrl: './my-articles.component.html',
  styleUrls: ['./my-articles.component.css']
})
export class MyArticlesComponent implements OnInit {

  articles : Object;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.articleService.getUserArticles().subscribe(
      (data) => { this.articles = data
      console.log(this.articles);
      }
    )
  }


}
