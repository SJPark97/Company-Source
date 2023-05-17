import { useSelector } from "react-redux";
import { RootState } from "@/stores/store";
import UnselectedCard from "./UnselectedCard";
import SelectedCard from "./SelectedCard";
import cardLocation from "@/models/cardLocation";


export default function CompanyComparisonCard({ cardLocation }: cardLocation) {

  const isLeftCardSelected = useSelector((state: RootState) => {
    return state.leftSelectedCompany.isSelected
  })
  const isRightCardSelected = useSelector((state: RootState) => {
    return state.rightSelectedCompany.isSelected
  })


  if (cardLocation === "left" && !isLeftCardSelected) {
    return (
      <>
        <UnselectedCard cardLocation="left" />
      </>
    )
  } else if (cardLocation === "right" && !isRightCardSelected) {
    return (
      <>
        <UnselectedCard cardLocation="right" />
      </>
    )
  } else if (cardLocation === "left" && isLeftCardSelected) {
    return (
      <>
        <SelectedCard cardLocation="left" />
      </>
    )
  } else {
    return (
      <>
        <SelectedCard cardLocation="right" />
      </>
    )
  }

}


