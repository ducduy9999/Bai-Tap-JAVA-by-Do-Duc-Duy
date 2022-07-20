export const findCategories = async search => {
  var config = {
    method: 'post',
    url: '/category/search',
    data: search,
  };
  try {
    let response = await axiosInstance(config);

    let result = response.data; // ko co gi
    return {code: 200, result};
  } catch (error) {
    console.log(error);

    if (error.response) {
      return {code: error.response.status};
    } else if (error.request) {
      return {code: 408};
    } else {
      return {code: 500};
    }
  }
};
