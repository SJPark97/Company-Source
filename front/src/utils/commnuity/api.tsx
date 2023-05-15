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
    return response;
  } catch (error) {
    console.log(error);
    return null;
  }
};

export const createFreeAxios = async (
  content: string,
  title: string,
  myCookie: string
) => {
  try {
    console.log(myCookie);
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
    return response;
  } catch (error) {
    console.log(error);
    return null;
  }
};

// 기업 게시물 수정 Axios
export const modifyCorpAxios = async (
  content: string,
  id: string,
  title: string,
  accessToken: string
) => {
  try {
    const response = await axios.put(
      SERVER_URL + "/community/corp",
      {
        content,
        id,
        title,
        accessToken,
      },
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
    return response;
  } catch (error) {
    return null;
  }
};

export const modifyFreeAxios = async (
  content: string,
  id: string,
  title: string,
  accessToken: string
) => {
  try {
    const response = await axios.put(
      SERVER_URL + "/community/free",
      {
        content,
        id,
        title,
        accessToken,
      },
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
    return response;
  } catch (error) {
    return null;
  }
};

export const deleteCorpDetailAxios = async (
  communityId: string,
  accessToken: string
) => {
  try {
    const response = await axios.delete(
      SERVER_URL + "/community/corp/" + `${communityId}`,
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
    return response;
  } catch (error) {
    return null;
  }
};

export const likeDetailAxios = async (
  accessToken: string,
  communityId: string
) => {
  try {
    const response = await axios.post(
      SERVER_URL + "/likes/" + `${communityId}`,
      {},
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
    return response;
  } catch (error) {
    return null;
  }
};

export const cancelLikeDetailAxios = async (
  accessToken: string,
  communityId: string
) => {
  try {
    const response = await axios.delete(
      SERVER_URL + "/likes/" + `${communityId}`,
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
    return response;
  } catch (error) {
    return null;
  }
};

// 기업게시판 디테일 불러오는 axios
export const getCorpBoardDetail = async (communityId: string) => {
  try {
    const response = await axios.get(
      SERVER_URL + "/community/corp/" + `${communityId}`
    );
    return response;
  } catch (error) {
    return null;
  }
};

// 자유게시판 디테일 불러오는 axios
export const getFreeBoardDetail = async (communityId: string) => {
  try {
    const response = await axios.get(
      SERVER_URL + "/community/free/" + `${communityId}`
    );
    return response;
  } catch (error) {
    return null;
  }
};

// 부모댓글 생성 Axios
export const createComment = async (
  // 부모댓글이면 parent : 1
  // 대댓글이면 parent : 0

  // 부모면 commentGroup : 0
  // 자식이면 commentGroup : 부모의 commentGroup
  accessToken: string,
  communityId: string,
  content: string
) => {
  try {
    const response = await axios.post(
      SERVER_URL + "/comment",
      {
        commentGroup: 0,
        communityId,
        content,
        parent: 1,
      },
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
    return response;
  } catch (error) {
    return null;
  }
};

// 대댓글 생성 Axios
export const createReply = async (
  commentGroup: number,
  communityId: string,
  content: string,
  accessToken: string
) => {
  try {
    const response = await axios.post(
      SERVER_URL + "/comment",
      {
        commentGroup,
        communityId,
        content,
        parent: 0,
      },
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
    return response;
  } catch (error) {}
};

// 댓글 삭제 Axios
export const deleteComment = async (commentId: number, accessToken: string) => {
  try {
    const response = await axios.delete(
      SERVER_URL + "/comment" + `/${commentId}`,
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
    return response;
  } catch (error) {
    return null;
  }
};

// 댓글 수정 Axios
export const modifyComment = async (
  commentId: number,
  content: string,
  accessToken: string
) => {
  try {
    const response = await axios.put(
      SERVER_URL + "/comment",
      {
        commentId,
        content,
      },
      {
        headers: {
          Authorization: accessToken,
        },
      }
    );
  } catch (error) {
    return null;
  }
};
