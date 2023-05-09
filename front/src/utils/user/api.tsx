import axios from "axios";
import { SERVER_URL } from "../url";

export const idCheckAxios = async (id: string) => {
  try {
    const response = await axios.get(SERVER_URL + `/user/validusername/${id}`);
    return response.data;
  } catch (error) {
    console.error(error);
    return null;
  }
};

export const nickNameCheckAxios = async (nickName: string) => {
  try {
    const response = await axios.get(
      SERVER_URL + `/user/validnickname/${nickName}`
    );
    return response.data;
  } catch (error) {
    console.error(error);
    return null;
  }
};
