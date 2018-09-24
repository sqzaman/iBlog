import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../authentication/services/article.service';

@Component({
  selector: 'app-admin-all-article',
  templateUrl: './admin-all-article.component.html',
  styleUrls: ['./admin-all-article.component.css']
})
export class AdminAllArticleComponent implements OnInit {

  articles : Object;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.articleService.getAllArticles().subscribe(
      (data) => { this.articles = data
      console.log(this.articles);
      }
    )
  }

}
