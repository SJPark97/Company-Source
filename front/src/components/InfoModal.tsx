import Modal from '@mui/material/Modal';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/stores/store';
import { closeInfoModal } from '@/stores/info/controlInfoDetail';

export default function InfoModal() {

  const infoModalState = useSelector((state: RootState) => {
    return state.controlInfoDetail.isOpen
  })
  const dispatch = useDispatch();

  return (
    <div>
      <Modal
        style={{ backgroundColor: 'transparent' }}
        open={infoModalState}
        onClose={() => dispatch(closeInfoModal())}
      // aria-labelledby="info-modal-modal-title"
      // aria-describedby="info-modal-modal-description"
      >
        <div className='flex flex-col items-center border-brand border-3 border-solid rounded-5 bg-white w-[600px] max-h-[500px] absolute top-[20%] left-[50%] translate-x-[-50%]'>
          <div className='h-[300px]'>

          </div>
        </div>
      </Modal>
    </div >
  );
}