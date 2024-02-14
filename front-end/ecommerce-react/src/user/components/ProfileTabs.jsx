import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { Subscriptions } from './Subscriptions';
import axios from 'axios';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import { styled } from '@mui/material/styles';
import Swal from 'sweetalert2';

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));

function CustomTabPanel(props) {
    const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

CustomTabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

export const ProfileTabs = () => {
  const [value, setValue] = React.useState(0);
  const [data, setData] = useState(null);

    useEffect(() => {
        axios.get("http://localhost:8080/api/user/profile", {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        })
        .then(response => {
            setData(response.data);
        })
        .catch(error => {
            Swal.fire('Error', error.response.data.text, 'error');
            console.error('Error fetching data:', error);
        });
    }, []);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Box sx={{ width: '100%' }}>
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs 
            value={value} 
            onChange={handleChange} 
            aria-label="basic tabs example" 
            variant="fullWidth" 
            centered
        >
          <Tab label="User Details" className='user-tab' {...a11yProps(0)} />
          <Tab label="Subscriptions" className='user-tab' {...a11yProps(1)} />
          <Tab label="Subscriptions content" className='user-tab' disabled={data === null || data.onlySubscribersContent === null} {...a11yProps(2)} />
        </Tabs>
      </Box>
      <CustomTabPanel value={value} index={0}>
        <Box sx={{ width: '100%' }}>
            <Stack spacing={2}>
                <Item><b>Name:</b> {data ? data.name : 'Loading...'}</Item>
                <Item><b>Last name:</b> {data ? data.lastName : 'Loading...'}</Item>
                <Item><b>Email:</b> {data ? data.email : 'Loading...'}</Item>
                <Item><b>Receive newsletter: </b> {data ? (data.receiveNewsletter === true ? 'Yes' : 'No') : 'Loading...'}</Item>
            </Stack>
        </Box>
      </CustomTabPanel>
      <CustomTabPanel value={value} index={1}>
        {
            (data === null || data.onlySubscribersContent === null) 
            ?
            (
                <Subscriptions
                    subscriptionsList = {data === null ? null : data.availableSubscriptionTypeList}
                />
            )
            :
            (
                <Box sx={{ width: '100%' }}>
                    <Stack spacing={2}>
                        <Item><b>Subscription type:</b> {data ? data.userSubscriptionType.description : 'Loading...'}</Item>
                        <Item><b>Subscription state:</b> {data ? data.subscriptionState.description : 'Loading...'}</Item>
                        <Item>
                            <b>Start date: </b> 
                            {data ? 
                                `${new Date(data.startDate).getDate()}/${new Date(data.startDate).getMonth() + 1}/${new Date(data.startDate).getFullYear()}`
                                : 'Loading...'
                            }
                        </Item>
                        <Item>
                            <b>Expiration date: </b> 
                            {data ? 
                                `${new Date(data.expirationDate).getDate()}/${new Date(data.expirationDate).getMonth() + 1}/${new Date(data.expirationDate).getFullYear()}` 
                                : 'Loading...'
                            }
                        </Item>
                    </Stack>
                </Box>
            )
                        
        }

        
        
      </CustomTabPanel>
      <CustomTabPanel value={value} index={2}>
        Content only for subscribers
      </CustomTabPanel>
    </Box>
  );
}