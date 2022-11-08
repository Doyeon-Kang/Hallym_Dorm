import http from "../http-common";

class StorePhotoService {
  getAll(id) {
    return http.get(`/board-store/${id}/photos`);
  }

  get(id, photo_id) {
    return http.get(`/board-store/${id}/photo/${photo_id}`);
  }

  create(id, photo) {
    let formData = new FormData();

    formData.append('photos', photo);

    return http.post(`/board-store/${id}/upload`, 
    formData,
    {
      headers: {
          'Content-Type': 'multipart/form-data'
      }
    }
    ).then(function(){
      console.log('SUCCESS!!');
    })
    .catch(function(){
      console.log('FAILURE!!');
    });
  }

  delete(id, photo_id) {
    return http.delete(`/board-store/${id}/photo/${photo_id}`);
  }
}

export default new StorePhotoService();