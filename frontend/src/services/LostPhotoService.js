import http from "../http-common";

class LostPhotoService {
  getAll(id) {
    return http.get(`/board-lost/${id}/photos`);
  }

  get(id, photo_id) {
    return http.get(`/board-lost/${id}/photo/${photo_id}`);
  }

  create(id, photo) {
    return http.post(`/board-lost/${id}/upload`, photo);
  }

  delete(id, photo_id) {
    return http.delete(`/board-lost/${id}/photo/${photo_id}`);
  }
}

export default new LostPhotoService();