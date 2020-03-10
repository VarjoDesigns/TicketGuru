import React, { useState, useEffect } from 'react';
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1
  },
  paper: {
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.secondary
  }
}));

export default function Users() {
  //const [title, setTitle] = useState('Home');
  const [users, setUsers] = useState([]);
  const classes = useStyles();
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => fetchUsers(), []);

  const fetchUsers = () => {
    setIsLoading(true);
    fetch('http://localhost:8080/api/users')
      .then(response => response.json())
      .then(data => setUsers(data._embedded.users));
    setIsLoading(false);
  };

  console.log(users);

  /*
  const deleteCustomer = link => {
    fetch(link, { method: 'DELETE' })
      .then(response => fetchUsers())
      .catch(err => console.error(err));
  };

  const updateCustomer = (customer, link) => {
    fetch(link, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(customer)
    })
      .then(response => fetchUsers())
      .catch(err => console.error(err));
  };

  const saveWorkout = workout => {
    fetch('https://customerrest.herokuapp.com/api/trainings', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(workout)
    })
      .then(response => fetchUsers())
      .catch(err => console.error(err));
  };

  const saveCustomer = customer => {
    fetch('https://customerrest.herokuapp.com/api/customers', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(customer)
    })
      .then(response => fetchUsers())
      .catch(err => console.error(err));
  };

  const onItemClick = title => () => {
    setTitle(title);
  };

  */

  const columns = [
    {
      Header: 'Name',
      accessor: 'name'
    },
    {
      Header: 'Password',
      accessor: 'password'
    },
    {
      Header: 'User Group',
      accessor: '_links.userGroups.href'
    }
  ];

  return (
    <div className={classes.root}>
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <Paper className={classes.paper}>USERS</Paper>
        </Grid>
        <Grid item xs={12}>
          {isLoading ? (
            <div>Loading ...</div>
          ) : (
            <ReactTable
              minRows={10}
              filterable={true}
              data={users}
              columns={columns}
            />
          )}
        </Grid>
      </Grid>
    </div>
  );
}
