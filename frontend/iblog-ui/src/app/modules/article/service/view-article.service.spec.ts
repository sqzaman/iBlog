import { TestBed, inject } from '@angular/core/testing';

import { ViewArticleService } from './view-article.service';

describe('ViewArticleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ViewArticleService]
    });
  });

  it('should be created', inject([ViewArticleService], (service: ViewArticleService) => {
    expect(service).toBeTruthy();
  }));
});
