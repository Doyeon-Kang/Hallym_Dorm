import http from "../http-common";

class LostPhotoService {
  getAll(id) {
    return http.get(`/board-lost/${id}/photos`);
  }

  get(id, photo_id) {
    return http.get(`/board-lost/${id}/photo/${photo_id}`);
  }

  create(id, photo) {
    let formData = new FormData();

    formData.append('photos', photo);

    return http.post(`/board-lost/${id}/upload`, 
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
    return http.delete(`/board-lost/${id}/photo/${photo_id}`);
  }
}

export default new LostPhotoService();