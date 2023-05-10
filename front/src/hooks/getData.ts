import { SERVER_URL } from "@/utils/url";
import axios from "axios";

const getData = (analysisCode: string, companyId: string) => axios.get(SERVER_URL + `/${analysisCode}/${companyId}`)


export default getData;