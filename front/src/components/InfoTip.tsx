import { styled } from '@mui/material/styles';
import Tooltip, { TooltipProps, tooltipClasses } from '@mui/material/Tooltip';

const InfoTip = styled(({ className, ...props }: TooltipProps) => (
  <Tooltip {...props} classes={{ popperArrow: className }} />
))(({ theme }) => ({
  [`& .${tooltipClasses.tooltip}`]: {
    backgroundColor: 'rgba(28, 127, 243, 0.9)',
    color: '#FFFFFF',
    padding: '20px',
    boxShadow: theme.shadows[1],
    fontSize: 14,
  },
}));

export default InfoTip;