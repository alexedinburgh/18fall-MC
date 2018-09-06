bool turn, flag[2];		// the shared variables, booleans
byte ncrit;        		// nr of procs in critical section

active [2] proctype user()	// two processes
{
	assert(_pid == 0 || _pid == 1);
again:
	turn = _pid;
	flag[_pid] = 1;
	(flag[1 - _pid] == 0 || turn == 1 - _pid);

	ncrit++;
	assert(ncrit == 1);	// critical section
	ncrit--;

	flag[_pid] = 0;
	goto again
}