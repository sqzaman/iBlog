import { Component, OnInit } from '@angular/core';
import { ViewArticleService } from '../service/view-article.service';
import { ActivatedRoute } from '@angular/router';
import {TokenStorage} from '../../../modules/authentication/components/signin/service/token.storage';
import {Comment} from '../model/Comment';

@Component({
  selector: 'app-view-article',
  templateUrl: './view-article.component.html',
  styleUrls: ['./view-article.component.css']
})
export class ViewArticleComponent implements OnInit {
  userLoggedIn: boolean;
  article: any;
  comments: any;
  articleId: any;
  submitted:  false;
  success:  false;
  failed: false;
  message: '';
  comment: Comment =  new Comment();

  newComment(): void {
    this.submitted = false;
    this.comment = new Comment();
  }



  constructor(private viewArticleService: ViewArticleService, private token: TokenStorage, private route: ActivatedRoute) {
    this.userLoggedIn = token.isUserLoggedIn();
    this.comment = new Comment();
  }
  save() {
    console.log('inside save');
    this.viewArticleService.insertComment(this.comment, this.articleId)
      .subscribe((data) => {
        console.log(data);
        this.success = JSON.parse(JSON.stringify(data)).ok;
        this.message = JSON.parse(JSON.stringify(data)).message;
        this.comment = new Comment();
        console.log('message ' + this.message);
        console.log('success ' + this.success);
      });
  }
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.articleId = params['id'];
    });
    this.viewArticleService.getArticles(this.articleId).subscribe(
      (data) => {
        this.article = data;
        console.log(Error);
      }
    );
    this.viewArticleService.getComments(this.articleId).subscribe(
      (data) => {
        this.comments = data;
        console.log(this.comments);
      }
    );
  }
  onSubmit() {
    this.success = false;
    this.failed = false;
    this.message = '';
    this.save();
  }
}
