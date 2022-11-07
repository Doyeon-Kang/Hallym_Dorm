import http from "../http-common";

class RepairSubcommentService {
  getAll(id, com_id) {
    return http.get(`/board-repair/${id}/comments/${com_id}/subcomments`);
  }

  get(com_id) {
    return http.get(`/repair-subcomments/${com_id}`);
  }

  create(id, com_id, data) {
    return http.post(`/board-repair/${id}/comments/${com_id}/subcomments`, data);
  }

  update(com_id, data) {
    return http.put(`/repair-subcomments/${com_id}`, data);
  }

  delete(com_id) {
    return http.delete(`/repair-subcomments/${com_id}`);
  }

  deleteAll(id, com_id) {
    return http.delete(`/board-repair/${id}/comments/${com_id}/subcomments`);
  }
}

export default new RepairSubcommentService();