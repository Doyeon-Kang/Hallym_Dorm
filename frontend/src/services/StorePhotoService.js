import http from "../http-common";

class StorePhotoService {
  getAll(id) {
    return http.get(`/board-store/${id}/photos`);
  }

  get(id, photo_id) {
    return http.get(`/board-store/${id}/photo/${photo_id}`);
  }

  create(id, data) {
    return http.post(`/board-store/${id}/upload`, data);
  }

  delete(id, photo_id) {
    return http.delete(`/board-store/${id}/photo/${photo_id}`);
  }
}

export default new StorePhotoService();