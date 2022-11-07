import http from "../http-common";

class NewsCommentDataService {
  getAll(newsId) {
    return http.get(`/board-news/${newsId}/comments`);
  }

  get(id) {
    return http.get(`/news-comments/${id}`);
  }

  create(newsId, data) {
    return http.post(`/board-news/${newsId}/comments`, data);
  }

  update(id, data) {
    return http.put(`/news-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/news-comments/${id}`);
  }

  deleteAll(newsId) {
    return http.delete(`/board-news/${newsId}/comments`);
  }
}

export default new NewsCommentDataService();