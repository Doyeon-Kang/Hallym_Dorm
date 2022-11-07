import http from "../http-common";

class NewsSubcommentService {
  getAll(id, com_id) {
    return http.get(`/board-news/${id}/comments/${com_id}/subcomments`);
  }

  get(com_id) {
    return http.get(`/news-subcomments/${com_id}`);
  }

  create(id, com_id, data) {
    return http.post(`/board-news/${id}/comments/${com_id}/subcomments`, data);
  }

  update(com_id, data) {
    return http.put(`/news-subcomments/${com_id}`, data);
  }

  delete(com_id) {
    return http.delete(`/news-subcomments/${com_id}`);
  }

  deleteAll(id, com_id) {
    return http.delete(`/board-news/${id}/comments/${com_id}/subcomments`);
  }
}

export default new NewsSubcommentService();