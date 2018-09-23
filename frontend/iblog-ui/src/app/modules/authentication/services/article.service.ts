import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Globals } from '../../../shared/config/globals.service';
import { Article } from '../../authentication/components/profile/model/article';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpClient,  private globals: Globals) { }
 
  createArticle(article: Object): Observable<Object> {
    //console.log(article);
    return this.http.post(this.globals.BASE_API_URL + 'blog/create', article);
  }
 
  getUserArticles(): Observable<Object> {
    return this.http.get(this.globals.BASE_API_URL + 'blog/get/user/articles');
  }

  getArticle(id: Number ): Observable<Object> {
    return this.http.get(this.globals.BASE_API_URL + "blog/get/" + id);
  }

  saveArticle(article: Article, articleId: number ): Observable<Object> {
    let url = "";
    if(articleId == null) {
      url = this.globals.BASE_API_URL + "blog/create";
    } else {
      url = this.globals.BASE_API_URL + "blog/update/" + articleId;
    }

    return this.http.post(url, article);
  }

  getAllArticles(): Observable<Object> {
    return this.http.get(this.globals.BASE_API_URL + 'blog/get/all/articles');
  }
}
