


const URL_GET_CONTACTS='http://localhost:8080/api/user/';


export function getUsers() {
   
    return request({
      url: URL_GET_CONTACTS,
      method: "GET",
    });
  }


const request = (options) => {

  return fetch(options.url, options).then((response) =>
    response.json().then((json) => {
      if (!response.ok) {
        return Promise.reject(json);
      }
      return json;
    })
  );
};
