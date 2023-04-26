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

const chartQueryClient = (companyId: string) => {
  const queryClient = new QueryClient();
  // console.log(companyId)
  Promise.all(
    analysisCodeList.map((analysisCode: IanaysisCode) => {
      const getChartData = async () => {
        const { data } = await axios.get(SERVER_URL + `/${analysisCode.id}/${companyId}`)
        // console.log(data)
        return data;
      }
      queryClient.prefetchQuery(['analysis', analysisCode.id], getChartData);
      // console.log(queryClient);
    })
  )
  return queryClient;
}

export default chartQueryClient;