import http from "../http-common";

class RepairCommentDataService {
  getAll(repairId) {
    return http.get(`/board-repair/${repairId}/comments`);
  }

  get(id) {
    return http.get(`/repair-comments/${id}`);
  }

  create(repairId, data) {
    return http.post(`/board-repair/${repairId}/comments`, data);
  }

  update(id, data) {
    return http.put(`/repair-comments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/repair-comments/${id}`);
  }

  deleteAll(repairId) {
    return http.delete(`/board-repair/${repairId}/comments`);
  }
}

export default new RepairCommentDataService();