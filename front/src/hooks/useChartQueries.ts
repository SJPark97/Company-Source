import { SERVER_URL } from "@/utils/url";
import axios from "axios";
import { useQueries, useQuery } from "react-query";

interface IanaysisCode {
  id: string;
}

const analysisCodeList: IanaysisCode[] = [{ id: "101" }, { id: "103" }];

const useChartQueries = (companyId: string) => {
  return useQueries(
    analysisCodeList.map((analysisCode: IanaysisCode) => {
      return {
        // queryKey: ["analysis", analysisCode.id],
        queryKey: `["analysis", ${analysisCode.id}]`,
        queryFn: () =>
          axios.get(SERVER_URL + `/${analysisCode.id}/${companyId}`),
      };
    })
  );
};

export default useChartQueries;
