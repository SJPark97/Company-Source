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
