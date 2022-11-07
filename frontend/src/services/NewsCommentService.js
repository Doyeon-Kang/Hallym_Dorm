import http from "../http-common";

class NewsCommentService {
  getAll(id) {
    return http.get(`/board-news/${id}/comments`);
  }

  get(id) {
    return http.get(`/news-comments/${id}`);
  }

  create(id, data) {
    return http.post(`/board-news/${id}/comments`, data);
  }

  update(id, data) {
    return http.put(`/news-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/news-comments/${id}`);
  }

  deleteAll(id) {
    return http.delete(`/board-news/${id}/comments`);
  }
}

export default new NewsCommentService();