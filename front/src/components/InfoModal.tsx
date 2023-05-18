import Modal from '@mui/material/Modal';
import formatDescription from '@/utils/formatDescription';
import InfoContent from '@/models/InfoContent';
import { List, ListItem, ListItemText, Typography } from '@mui/material';
import { ReactElement, cloneElement, useState } from 'react';
import SecondaryText from './InfoItemText';
import InfoItemText from './InfoItemText';

interface Iprops {
  open: boolean,
  handleClose: () => void,
  handleOpen: () => void,
  analysisInfo: { [key: string]: string },
}


export default function InfoModal({ analysisInfo, open, handleClose, handleOpen }: Iprops) {
  const [secondaryContentOne, setSecondaryContentOne] = useState(false);
  const [secondaryContentTwo, setSecondaryContentTwo] = useState(false);
  const [secondaryContentThree, setSecondaryContentThree] = useState(false);
  let i = 0;

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby={`${analysisInfo.analysis_id}-info-modal-modal-title`}
        aria-describedby={`${analysisInfo.analysis_id}-info-modal-modal-description`}
      >
        <div className='flex flex-col items-start p-40 border-brand border-3 border-solid rounded-5 bg-white top-[5%] w-[90%] h-[90%] absolute left-[50%] translate-x-[-50%]'>
          <button onClick={handleClose} className='absolute bottom-0 right-0 py-12 m-20 font-bold text-white bg-red-600 px-30 text-16 rounded-5'>닫기</button>
          <h1 className='font-bold text-28'>
            {InfoContent.analysisInfo.analysis_name}
          </h1>
          {/* <button onClick={() => setSecondary(!secondary)} className='absolute top-0 left-0 px-5 py-2 m-20 text-white bg-brand rounded-2'>확장하기</button> */}
          <List className='flex'>
            {InfoContent.analysisInfo.analysis_detail.map((items) => {
              i += 1;
              return (
                <>
                  <div className='flex flex-col p-10 max-w-[32%]'>
                    <div className='px-10 py-8 bg-blue-100 text-18 rounded-5'>{items.category}</div>
                    <div className='my-10 pl-15'>
                      {items.value.map((item) => {
                        if (item.title === "") {
                          return (
                            <div className='mb-10 text-14'>{item.content}</div>
                          )
                        } else {
                          return (
                            <>
                              <ListItem onClick={() => setSecondaryContentOne(!secondaryContentOne)} sx={{ p: 0, }} className='hover:bg-slate-100 rounded-5'>
                                <InfoItemText item={item} />
                              </ListItem>
                            </>
                          )
                        }
                      })}
                    </div>
                  </div>
                </>
              )
            })}
          </List>

          {/* <div className='overflow-y-scroll'>{formatDescription(analysisInfo.analysis_detail)}</div> */}
        </div>
      </Modal >
    </div >
  );
}