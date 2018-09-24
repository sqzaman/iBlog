import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../authentication/services/article.service';

@Component({
  selector: 'app-admin-approval-need-article',
  templateUrl: './admin-approval-need-article.component.html',
  styleUrls: ['./admin-approval-need-article.component.css']
})
export class AdminApprovalNeedArticleComponent implements OnInit {

  articles: Object;
  success: boolean = false;
  failed: boolean = false;
  message: string = "";

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.articleService.getAllArticlesByStatus(0).subscribe(
      (data) => {
      this.articles = data
        console.log(this.articles);
      }
    )
  }

  approveMe(articleId) {
    this.articleService.updateArticleStatus(articleId, 1).subscribe(
      (data) => {
        this.success = JSON.parse(JSON.stringify(data)).success;
        this.message = JSON.parse(JSON.stringify(data)).message;
        document.getElementById("article-id-" + articleId).remove();
        //console.log(document.getElementById("total-unapproved-article-count").innerHTML);
        let remainToApprove = parseInt(document.getElementById("total-unapproved-article-count").innerHTML) - 1;
        document.getElementById("total-unapproved-article-count").innerHTML =  remainToApprove.toString()
      }, (error) => { 
          this.failed = !JSON.parse(JSON.stringify(error.error)).success;
          this.message = JSON.parse(JSON.stringify(error.error)).message;
          document.getElementById("article-id-" + articleId).remove();
      }
    )
  }

  rejectMe(articleId) {
    this.articleService.updateArticleStatus(articleId, 2).subscribe(
      (data) => {
        this.success = JSON.parse(JSON.stringify(data)).success;
        this.message = JSON.parse(JSON.stringify(data)).message;
        document.getElementById("article-id-" + articleId).remove();
        let remainToApprove = parseInt(document.getElementById("total-unapproved-article-count").innerHTML) - 1;
        document.getElementById("total-unapproved-article-count").innerHTML =  remainToApprove.toString()
      }, (error) => { 
          this.failed = !JSON.parse(JSON.stringify(error.error)).success;
          this.message = JSON.parse(JSON.stringify(error.error)).message;
      }
    )
  }

}
