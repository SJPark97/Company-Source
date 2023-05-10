import { QueryClient } from "react-query";
import getData from "./getData";

interface IanaysisCode {
  id: string;
}

const analysisCodeList: IanaysisCode[] = [{ id: "101" }, { id: "103" }];

const chartQueryClient = (companyId: string) => {
  const queryClient = new QueryClient();
  Promise.all(
    analysisCodeList.map((analysisCode: IanaysisCode) => {
      queryClient.prefetchQuery(["analysis", analysisCode.id, companyId], () => getData(analysisCode.id, companyId));
    })
  );
  return queryClient;
};

export default chartQueryClient;
