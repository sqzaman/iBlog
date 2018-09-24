import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Globals } from '../../../shared/config/globals.service';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
 
  constructor(private http: HttpClient, private globals: Globals) { }

  getArticles(searchText: string = "", limit: number = 10): Observable<Object> {
    let elasticSearchUrl = "http://localhost:9200/article_index/article/_search";
    let size = "?size=" + limit;
    if(searchText != "" && searchText != null) {
      elasticSearchUrl = elasticSearchUrl + "?q=body:"+ searchText + " OR title:"+ searchText ;
      let size = "&size="+ limit;
    }


    return this.http.get(elasticSearchUrl + size);
  }

}
