import React from "react";

const formatDescription = (text: string) => {
  return text.split('\n').map((line, index) => (
    <React.Fragment key={index} >
      {line}
      < br />
    </React.Fragment>
  ));
};

export default formatDescription;