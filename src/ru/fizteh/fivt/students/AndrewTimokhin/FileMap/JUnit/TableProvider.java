package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

public interface TableProvider {

    /**
     * ���������� ������� � ��������� ���������.
     *
     * @param name
     *            �������� �������.
     * @return ������, �������������� �������. ���� ������� � ��������� ������
     *         �� ����������, ���������� null.
     * @throws IllegalArgumentException
     *             ���� �������� ������� null ��� ����� ������������ ��������.
     */
    Table getTable(String name);

    /**
     * ������ ������� � ��������� ���������.
     *
     * @param name
     *            �������� �������.
     * @return ������, �������������� �������. ���� ������� ��� ����������,
     *         ���������� null.
     * @throws IllegalArgumentException
     *             ���� �������� ������� null ��� ����� ������������ ��������.
     */
    Table createTable(String name);

    /**
     * ������� ������� � ��������� ���������.
     *
     * @param name
     *            �������� �������.
     * @throws IllegalArgumentException
     *             ���� �������� ������� null ��� ����� ������������ ��������.
     * @throws IllegalStateException
     *             ���� ������� � ��������� ��������� �� ����������.
     */
    void removeTable(String name);
}
