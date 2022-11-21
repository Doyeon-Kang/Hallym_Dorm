import http from "../http-common";

class NewsDataService {
  getAll() {
    return http.get("/board-news");
  }

  get(id) {
    return http.get(`/board-news/${id}`);
  }

  getMy(studentNo) {
    return http.get(`/board-news/my-news?studentNo=${studentNo}`);
  }

  create(data) {
    return http.post("/board-news", data);
  }

  update(id, data) {
    return http.put(`/board-news/${id}`, data);
  }

  delete(id) {
    return http.delete(`/board-news/${id}`);
  }

  deleteAll() {
    return http.delete(`/board-news`);
  }
}

export default new NewsDataService();