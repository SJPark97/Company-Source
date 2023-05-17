import NavBar from "@/components/NavBar";
import { SERVER_URL } from "@/utils/url";
import { GetServerSidePropsContext } from "next";
import axios from "axios";

export default function CommunitySearchResult({ props }: { props: any }) {
  return (
    <>
      <NavBar />

      <div></div>
    </>
  );
}

export async function getServerSideProps(context: GetServerSidePropsContext) {
  console.log(context.query.searchWord);

  const { data } = await axios.get(SERVER_URL + "/community/all/search", {
    params: {
      content: context.query.searchWord,
      type: context.query.searchType,
      free_size: 5,
      free_page: 0,
      corp_size: 5,
      corp_page: 0,
    },
  });
  console.log(data);
  return {
    props: {
      data,
    },
  };
}
