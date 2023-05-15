import Modal from '@mui/material/Modal';
import ComparisonSearchBar from '../ComparisonSearchBar';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/stores/store';
import { closeLeftModal, closeRightModal } from '@/stores/comparison/controlModal';

interface IProps {
  modalLocation: "left" | "right"
}

export default function ComparisonModal({ modalLocation }: IProps) {

  const leftModalState = useSelector((state: RootState) => {
    return state.controlModal.isLeftOpen;
  })
  const rightModalState = useSelector((state: RootState) => {
    return state.controlModal.isRightOpen;
  })
  const dispatch = useDispatch();

  return (
    <div>
      <Modal
        open={modalLocation === "left" ? leftModalState : rightModalState}
        onClose={() => { modalLocation === "left" ? dispatch(closeLeftModal()) : dispatch(closeRightModal()) }}
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