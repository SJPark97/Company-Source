import axios from "axios";
import { SERVER_URL } from "../url";

export const allCorpPostAxios = async (page: number, size: number) => {
  try {
    const response = await axios.get(SERVER_URL + `/community/corp`, {
      params: {
        page,
        size,
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
    return null;
  }
};

export const createCorpAxios = async (
  content: string,
  title: string,
  myCookie: string
) => {
  try {
    const response = await axios.post(
      SERVER_URL + "/community/corp",
      {
        content,
        title,
      },
      {
        headers: {
          Authorization: myCookie,
        },
      }
    );
    console.log(myCookie);
    console.log(response);
    return response;
  } catch (error) {
    console.error(error);
    return null;
  }
};

export const createFreeAxios = async (
  content: string,
  title: string,
  myCookie: string
) => {
  try {
    const response = await axios.post(
      SERVER_URL + "/community/free",
      {
        content,
        title,
      },
      {
        headers: {
          Authorization: myCookie,
        },
      }
    );
    console.log(response);
    return response;
  } catch (error) {
    console.error(error);
    return null;
  }
};
