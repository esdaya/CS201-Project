import React from "react";
import { createStyles, Theme, makeStyles } from "@material-ui/core/styles";
import {
  Drawer,
  List,
  ListItem,
  ListItemText,
  ListSubheader,
  ListItemSecondaryAction,
  IconButton
} from "@material-ui/core";
import { PersonAdd as PersonAddIcon } from "@material-ui/icons";
import { Class } from "../../types";

interface SidebarProps {
  drawerWidth: number;
  classes: Class[];
  classId: number | null;
  setClassId: (id: number) => void;
}

const useStyles = makeStyles<Theme, SidebarProps>((theme: Theme) =>
  createStyles({
    drawer: ({ drawerWidth }) => ({
      width: drawerWidth,
      flexShrink: 0
    }),
    drawerPaper: ({ drawerWidth }) => ({
      width: drawerWidth
    }),
    toolbar: theme.mixins.toolbar
  })
);

const Sidebar: React.FC<SidebarProps> = props => {
  const classes = useStyles(props);

  return (
    <aside>
      <Drawer
        className={classes.drawer}
        variant="permanent"
        anchor="right"
        classes={{
          paper: classes.drawerPaper
        }}
      >
        <div className={classes.toolbar} />
        <List subheader={<ListSubheader>Classes</ListSubheader>}>
          {props.classes.map((value, index) => (
            <ListItem
              button
              selected={props.classId === value.class_id}
              onClick={() => props.setClassId(value.class_id)}
            >
              <ListItemText
                primary={value.name}
                secondary={value.students.length + " waiting"}
              />
              <ListItemSecondaryAction>
                <IconButton edge="end">
                  <PersonAddIcon />
                </IconButton>
              </ListItemSecondaryAction>
            </ListItem>
          ))}
        </List>
      </Drawer>
    </aside>
  );
};

export default Sidebar;
