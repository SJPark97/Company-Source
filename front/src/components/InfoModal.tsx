import Modal from '@mui/material/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/stores/store';
import { closeInfoModal } from '@/stores/info/controlInfoDetail';
import { Backdrop } from '@mui/material';

export default function InfoModal() {

  const infoModalState = useSelector((state: RootState) => {
    return state.controlInfoDetail.isOpen
  })
  const dispatch = useDispatch();

  return (
    <div>
      <Modal
        slots={{ backdrop: Backdrop }}
        slotProps={{
          backdrop: {
            sx: {
              //Your style here....
              backgroundColor: 'rgba(0, 0, 0, 0.1)',
            },
          }
        }}
        open={infoModalState}
        onClose={() => dispatch(closeInfoModal())}
        aria-labelledby="info-modal-modal-title"
        aria-describedby="info-modal-modal-description"
      >
        <div className='flex flex-col items-center border-brand border-3 border-solid rounded-5 bg-white w-[900px] max-h-[500px] absolute top-[20%] left-[50%] translate-x-[-50%]'>
          <div className='h-[300px]'>

          </div>
        </div>
      </Modal>
    </div >
  );
}