import http from "../http-common";

class NewsSubcommentDataService {
  getAll(newsId, commentId) {
    return http.get(`/board-news/${newsId}/comments/${commentId}/subcomments`);
  }

  get(id) {
    return http.get(`/news-subcomments/${id}`);
  }

  create(newsId, commentId, data) {
    return http.post(`/board-news/${newsId}/comments/${commentId}/subcomments`, data);
  }

  update(id, data) {
    return http.put(`/news-subcomments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/news-subcomments/${id}`);
  }

  deleteAll(newsId, commentId) {
    return http.delete(`/board-news/${newsId}/comments/${commentId}/subcomments`);
  }
}

export default new NewsSubcommentDataService();