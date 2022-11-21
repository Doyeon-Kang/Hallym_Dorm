import http from "../http-common";

class UserPointDataService {
  getAll() {
    return http.get(`/point`);
  }

  get(id) {
    return http.get(`/point/${id}`);
  }
  create(data) {
    return http.post(`/point`, data);
  }

  delete(id) {
    return http.delete(`/point/${id}`);
  }

  deleteAll() {
    return http.delete(`/point`);
  }
}

export default new UserPointDataService();