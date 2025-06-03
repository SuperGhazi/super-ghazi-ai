import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import { Grid, GridColumn, TabSheet, TabSheetTab } from '@vaadin/react-components';
import IncidentRecord from 'Frontend/generated/dev/ghazi/dto/IncidentRecord';
import UserRecord from 'Frontend/generated/dev/ghazi/dto/UserRecord';
import { IncidentService, UserService } from 'Frontend/generated/endpoints';
import { useEffect } from 'react';

export const config: ViewConfig = {
  menu: { order: 1, icon: 'line-awesome/svg/cog-solid.svg' },
  title: 'Administration',
  rolesAllowed: ['ADMIN'],
};

export default function AdministrationView() {

  const users = useSignal<UserRecord[]>([]);
  const incidents = useSignal<IncidentRecord[]>([]);

  useEffect(() => {
    UserService.findAllUsers().then((data) => {
      users.value = data;
    });
    IncidentService.findAllIncidents().then((data) => {
      incidents.value = data;
    });
  }, []);

  return (
    <div>
      <TabSheet>
        <TabSheetTab label="Users">
          <Grid items={users.value}>
            <GridColumn path="name" />
            <GridColumn path="function" />
            <GridColumn path="level" />
            <GridColumn path="department" />
            <GridColumn path="alive" />
          </Grid>
        </TabSheetTab>

        <TabSheetTab label="Incidents">
          <Grid items={incidents.value}>
            <GridColumn path="user.name" />
            <GridColumn path="reason" />
            <GridColumn path="timestamp" />
          </Grid>
        </TabSheetTab>
      </TabSheet>
    </div>
  );
}
