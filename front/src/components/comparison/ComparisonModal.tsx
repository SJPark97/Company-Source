import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { useState } from 'react';
import CompanyComparisonCard from './CompanyComparisonCard';
import ComparisonSearchBar from '../ComparisonSearchBar';

// const style = {
//   position: 'absolute' as 'absolute',
//   top: '50%',
//   left: '50%',
//   transform: 'translate(-50%, -50%)',
//   width: 400,
//   bgcolor: 'background.paper',
//   border: '2px solid #000',
//   boxShadow: 24,
//   p: 4,
// };

export default function ComparisonModal() {
  const [open, setOpen] = useState<boolean>(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      <button onClick={handleOpen}><CompanyComparisonCard /></button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <div className='flex flex-col items-center border-brand border-3 border-solid rounded-5 bg-white w-[600px] h-[400px] absolute top-[50%] left-[50%] translate-x-[-50%] translate-y-[-50%]'>
          <ComparisonSearchBar />
          <div className='text-black text-12'>
            Tip.
            <span className='ml-5 text-[#AAAAAA]'>
              검색된 기업의 특정 분석 지표가 존재하지 않을 수도 있습니다.
            </span>
          </div>
        </div>
      </Modal>
    </div>
  );
}