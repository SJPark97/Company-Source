import { SERVER_URL } from "@/utils/url";
import axios from "axios";
import { QueryClient } from "react-query";

interface IanaysisCode {
  id: string,
}

const analysisCodeList: IanaysisCode[] = [
  { id: "101" },
  { id: "103" },
];

const chartQueryClient = async (companyId: string) => {
  const queryClient = new QueryClient();
  await Promise.all(
    analysisCodeList.map((analysisCode: IanaysisCode) => {
      const getChartData = async () => {
        const { data } = await axios.get(SERVER_URL + `/${analysisCode.id}/${companyId}`)
        return data;
      }
      queryClient.prefetchQuery(['analysis', analysisCode.id], getChartData);
    })
  )
  return queryClient;
}

export default chartQueryClient;