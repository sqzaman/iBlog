import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../authentication/services/article.service';
import { ActivatedRoute } from "@angular/router";
import { TokenStorage } from '../../authentication/components/signin/service/token.storage';
import { PostComment } from '../models/post-comment';

@Component({
  selector: 'app-article-view',
  templateUrl: './article-view.component.html',
  styleUrls: ['./article-view.component.css']
})
export class ArticleViewComponent implements OnInit {

  articleId: number = null;
  article: Object;
  userLoggedIn: boolean; 
  postComment: PostComment = new PostComment();
  sub;

  constructor(private articleService: ArticleService, public route: ActivatedRoute,  private token: TokenStorage) {
    this.sub = this.route.url.subscribe(params => {
        this.articleId = Number.parseInt(params[1].path);
      })
      this.userLoggedIn = token.isUserLoggedIn();

    }

  ngOnInit() {
    if (this.articleId != null) {
      this.articleService.getArticle(this.articleId).subscribe(
        (data) => {
          this.article = data;
        }, (error) => {
        }
      )
    }
   }
  
  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  onSubmit(){
    this.articleService.postComment(this.postComment, this.articleId).subscribe(
      (data) => {
        //this.article = data;
       //let elem = document.getElementById("comment-container");
       //elem.insertBefore(this.makeNewCommentContent(data), elem.firstChild);
       location.reload();
      }, (error) => {
      }
    )
  }

  makeNewCommentContent(data){
    var para = document.createElement("div");

    var node = document.createTextNode("This is new.");
    para.appendChild(node);
    return para;

  }

}
