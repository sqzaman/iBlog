import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../authentication/services/article.service';

@Component({
  selector: 'app-admin-approval-need-article',
  templateUrl: './admin-approval-need-article.component.html',
  styleUrls: ['./admin-approval-need-article.component.css']
})
export class AdminApprovalNeedArticleComponent implements OnInit {

  articles: Object;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.articleService.getAllArticlesByStatus(0).subscribe(
      (data) => {
      this.articles = data
        console.log(this.articles);
      }
    )
  }

  rejectMe(articleId) {
    alert(articleId);
  }
  approveMe(articleId) {
    alert(articleId);
  }


}
