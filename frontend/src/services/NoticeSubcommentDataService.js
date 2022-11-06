import http from "../http-common";

class NoticeSubcommentDataService {
  getAll(noticeId, commentId) {
    return http.get(`/board-notice/${noticeId}/comments/${commentId}/subcomments`);
  }

  get(id) {
    return http.get(`/notice-subcomments/${id}`);
  }

  create(noticeId, commentId, data) {
    return http.post(`/board-notice/${noticeId}/comments/${commentId}/subcomments`, data);
  }

  update(id, data) {
    return http.put(`/notice-subcomments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/notice-subcomments/${id}`);
  }

  deleteAll(noticeId, commentId) {
    return http.delete(`/board-notice/${noticeId}/comments/${commentId}/subcomments`);
  }
}

export default new NoticeSubcommentDataService();