import http from "../http-common";

class NoticeSubcommentService {
  getAll(id, com_id) {
    return http.get(`/board-notice/${id}/comments/${com_id}/subcomments`);
  }

  get(com_id) {
    return http.get(`/notice-subcomments/${com_id}`);
  }

  create(id, com_id, data) {
    return http.post(`/board-notice/${id}/comments/${com_id}/subcomments`, data);
  }

  update(com_id, data) {
    return http.put(`/notice-subcomments/${com_id}`, data);
  }

  delete(com_id) {
    return http.delete(`/notice-subcomments/${com_id}`);
  }

  deleteAll(id, com_id) {
    return http.delete(`/board-notice/${id}/comments/${com_id}/subcomments`);
  }
}

export default new NoticeSubcommentService();