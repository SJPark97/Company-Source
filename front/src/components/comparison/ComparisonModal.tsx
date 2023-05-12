import Modal from '@mui/material/Modal';
import ComparisonSearchBar from '../ComparisonSearchBar';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/stores/store';
import { closeModal } from '@/stores/comparison/controlModal';

export default function ComparisonModal() {

  const modalState = useSelector((state: RootState) => {
    return state.controlModal.isOpen;
  })
  const dispatch = useDispatch();

  return (
    <div>
      <Modal
        open={modalState}
        onClose={() => { dispatch(closeModal()) }}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <div className='flex flex-col items-center border-brand border-3 border-solid rounded-5 bg-white w-[600px] max-h-[500px] absolute top-[20%] left-[50%] translate-x-[-50%]'>
          <ComparisonSearchBar />
        </div>
      </Modal>
    </div >
  );
}